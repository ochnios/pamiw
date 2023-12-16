using Blazored.LocalStorage;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using todo_pwa;
using todo_pwa.Authentication;
using todo_pwa.Services;

// helpful repo https://github.com/codingdroplets/BlazorServerAuthenticationAndAuthorization/blob/master/Pages/Login.razor

var builder = WebAssemblyHostBuilder.CreateDefault(args);
builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

builder.Services.AddAuthorizationCore();
builder.Services.AddBlazoredLocalStorageAsSingleton();
builder.Services.AddSingleton<AuthMessageHandler>();
builder.Services.AddSingleton(services =>
{
    var authMessageHandler = services.GetRequiredService<AuthMessageHandler>();
    return new HttpClient(authMessageHandler)
    {
        BaseAddress = new Uri(builder.Configuration.GetValue<String>("BaseAPIUrl"))
    };
});
builder.Services.AddScoped<AuthenticationStateProvider, CustomAuthStateProvider>();
builder.Services.AddScoped<AuthService>();

await builder.Build().RunAsync();
