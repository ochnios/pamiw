using System.Text;
using System.Text.Json;
using todo_maui.Models;

namespace todo_maui.Shared
{
    public class TaskService : ITaskService
    {
        private readonly HttpClient _httpClient;

        public TaskService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<Results<TodoTask>> GetTasksAsync()
        {
            var response = await _httpClient.GetAsync("tasks?pageNumber=1&pageSize=1000");
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<Results<TodoTask>>(content);
        }

        public async Task<TodoTask> GetTaskAsync(int id)
        {
            var response = await _httpClient.GetAsync($"tasks/{id}");
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<TodoTask>(content);
        }

        public async Task<TodoTask> CreateTaskAsync(TodoTask task)
        {
            var taskJson = new StringContent(JsonSerializer.Serialize(task), Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync("tasks", taskJson);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<TodoTask>(content);
        }

        public async Task<TodoTask> UpdateTaskAsync(int id, TodoTask task)
        {
            var taskJson = new StringContent(JsonSerializer.Serialize(task), Encoding.UTF8, "application/json");
            var response = await _httpClient.PatchAsync($"tasks/{id}", taskJson);
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<TodoTask>(content);
        }

        public async Task DeleteTaskAsync(int id)
        {
            var response = await _httpClient.DeleteAsync($"tasks/{id}");
            response.EnsureSuccessStatusCode();
        }
    }
}
