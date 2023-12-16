using Blazored.LocalStorage;
using Microsoft.AspNetCore.Components.Authorization;
using System.Net.Http.Json;
using todo_pwa.Authentication;
using todo_pwa.Models;

namespace todo_pwa.Services
{
    public class AuthService
    {
        private readonly HttpClient _http;
        private readonly ILocalStorageService _localStorage;
        private readonly CustomAuthStateProvider _authStateProvider;

        public AuthService(HttpClient http, ILocalStorageService localStorage, AuthenticationStateProvider authStateProvider)
        {
            _http = http;
            _localStorage = localStorage;
            _authStateProvider = (CustomAuthStateProvider) authStateProvider;
        }

        public async Task<Boolean> Login(LoginModel user)
        {
            var response = await _http.PostAsJsonAsync("/api/auth/login", user);

            if (response.IsSuccessStatusCode)
            {
                var result = await response.Content.ReadFromJsonAsync<AuthModel>();
                await _localStorage.SetItemAsStringAsync("jwt", result.AccessToken);
                _authStateProvider.SetUserLoggedIn(user.Email);

                return true;
            }

            return false;
        }

        public async Task<Boolean> Register(RegisterModel user)
        {
            var response = await _http.PostAsJsonAsync("/api/auth/register", user);

            if (response.IsSuccessStatusCode)
            {
                return true;
            }

            return false;
        }

        public async Task Logout() {
            await _localStorage.RemoveItemAsync("jwt");
            _authStateProvider.SetUserLoggedOut();
        }
    }
}
