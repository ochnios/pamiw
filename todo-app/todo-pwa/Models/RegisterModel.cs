
using System.Text.Json.Serialization;

namespace todo_pwa.Models
{
    public class RegisterModel
    {
        [JsonPropertyName("name")]
        public String? Name { get; set; }
        [JsonPropertyName("surname")]
        public String? Surname { get; set; }
        [JsonPropertyName("email")]
        public String? Email { get; set; }
        [JsonPropertyName("password")]
        public String? Password { get; set; }
    }
}
