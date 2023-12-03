using Blazored.LocalStorage;
using Microsoft.AspNetCore.Components.Authorization;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;

namespace todo_pwa.Authentication
{
    public class CustomAuthStateProvider : AuthenticationStateProvider
    {
        private readonly ILocalStorageService _localStorage;
        private ClaimsPrincipal _anonymous = new ClaimsPrincipal(new ClaimsIdentity());

        public CustomAuthStateProvider(ILocalStorageService localStorage)
        {
            _localStorage = localStorage;
        }

        public override async Task<AuthenticationState> GetAuthenticationStateAsync()
        {
            var token = await _localStorage.GetItemAsStringAsync("jwt");

            if (string.IsNullOrWhiteSpace(token) || IsTokenExpired(token))
            {
                return await Task.FromResult(new AuthenticationState(_anonymous));
            }

            var claimsPrincipal = new ClaimsPrincipal(new ClaimsIdentity(
                new List<Claim> { new Claim(ClaimTypes.Name, ParseTokenClaims(token)) }, "authToken"));

            return await Task.FromResult(new AuthenticationState(claimsPrincipal));
        }

        public async void SetUserLoggedIn(String email) {
            var claimsPrincipal = new ClaimsPrincipal(new ClaimsIdentity(
                new List<Claim> { new Claim(ClaimTypes.Name, email) }, "authUser"));
            var authenticationState = Task.FromResult(new AuthenticationState(claimsPrincipal));
            NotifyAuthenticationStateChanged(authenticationState);
        }

        public async void SetUserLoggedOut() {
            var authState = Task.FromResult(new AuthenticationState(_anonymous));
            NotifyAuthenticationStateChanged(authState);
        }

        private string ParseTokenClaims(string jwt)
        {
            var jwtSecurityTokenHandler = new JwtSecurityTokenHandler();
            return jwtSecurityTokenHandler.ReadJwtToken(jwt).Subject;
        }

        private bool IsTokenExpired(string jwt)
        {
            var jwtSecurityTokenHandler = new JwtSecurityTokenHandler();
            var jwtToken = jwtSecurityTokenHandler.ReadJwtToken(jwt);
            return jwtToken.ValidTo < DateTime.UtcNow;
        }
    }
}
