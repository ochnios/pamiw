using System.Text.Json.Serialization;

namespace todo_pwa.Models
{
    public class Results<T>
    {
        [JsonPropertyName("results")]
        public T[]? Data { get; set; }
        public int CurrentPage { get; set; }
        public int TotalPages { get; set; }
        public int TotalElements { get; set; }
    }
}
