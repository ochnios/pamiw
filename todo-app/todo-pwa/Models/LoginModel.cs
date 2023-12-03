using System.Text.Json.Serialization;

namespace todo_pwa.Models
{
    public class LoginModel
    {
        [JsonPropertyName("email")]
        public String? Email { get; set; }
        [JsonPropertyName("password")]
        public String? Password { get; set; }
    }
}
