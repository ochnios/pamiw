using System.Text;
using System.Text.Json;
using todo_maui.Models;

namespace todo_maui.Shared
{
    public class CategoryService : ICategoryService
    {
        private readonly HttpClient _httpClient;

        public CategoryService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<Results<Category>> GetCategoriesAsync()
        {
            var response = await _httpClient.GetAsync("categories?pageNumber=1&pageSize=1000");
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Results<Category>>(content);
        }

        public async Task<Category> GetCategoryAsync(int id)
        {
            var response = await _httpClient.GetAsync($"categories/{id}");
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Category>(content);
        }

        public async Task<Category> CreateCategoryAsync(Category Category)
        {
            var CategoryJson = new StringContent(JsonSerializer.Serialize(Category), Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync("categories", CategoryJson);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Category>(content);
        }

        public async Task<Category> UpdateCategoryAsync(int id, Category Category)
        {
            var CategoryJson = new StringContent(JsonSerializer.Serialize(Category), Encoding.UTF8, "application/json");
            var response = await _httpClient.PatchAsync($"categories/{id}", CategoryJson);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Category>(content);
        }

        public async Task DeleteCategoryAsync(int id)
        {
            var response = await _httpClient.DeleteAsync($"categories/{id}");
            response.EnsureSuccessStatusCode();
        }
    }
}
