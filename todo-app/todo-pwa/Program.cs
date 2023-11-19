using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using todo_pwa;

var builder = WebAssemblyHostBuilder.CreateDefault(args);
builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

builder.Services.AddSingleton(sp => new HttpClient { BaseAddress = new Uri(builder.Configuration.GetValue<String>("BaseAPIUrl")) });

await builder.Build().RunAsync();
