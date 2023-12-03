using Blazored.LocalStorage;
using Microsoft.AspNetCore.Components;
using System.Net.Http.Headers;

namespace todo_pwa.Authentication
{
    public class AuthMessageHandler : DelegatingHandler
    {
        private readonly ILocalStorageService _localStorage;
        private readonly NavigationManager _navManager;

        public AuthMessageHandler(ILocalStorageService localStorage, NavigationManager navManager)
        {
            _localStorage = localStorage;
            _navManager = navManager;
            InnerHandler = new HttpClientHandler();
        }

        protected override async Task<HttpResponseMessage> SendAsync(HttpRequestMessage request,
        CancellationToken cancellationToken)
        {
            var savedToken = await _localStorage.GetItemAsync<string>("jwt");

            if (!string.IsNullOrWhiteSpace(savedToken))
            {
                request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", savedToken);
            }

            var response = await base.SendAsync(request, cancellationToken);

            if (response.StatusCode == System.Net.HttpStatusCode.Unauthorized)
            {
                _navManager.NavigateTo("/login");
            }

            return response;
        }
    }
}
