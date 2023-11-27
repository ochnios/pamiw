using System.Text.Json.Serialization;

namespace todo_maui.Models
{
    public class Results<T>
    {
        [JsonPropertyName("results")]
        public T[]? Data { get; set; }

        [JsonPropertyName("currentPage")]
        public int CurrentPage { get; set; }

        [JsonPropertyName("totalPages")]
        public int TotalPages { get; set; }

        [JsonPropertyName("totalElements")]
        public int TotalElements { get; set; }
    }
}
