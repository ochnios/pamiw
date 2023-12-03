using System.Text.Json.Serialization;

namespace todo_pwa.Models
{
    public class AuthModel
    {
        [JsonPropertyName("accessToken")]
        public String? AccessToken { get; set; }
        [JsonPropertyName("tokenType")]
        public String? TokenType { get; set; }
    }
}
