using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using todo_maui.Models;

namespace todo_maui.Shared
{
    public interface ITaskService
    {
        Task<Results<TodoTask>> GetTasksAsync();
        Task<TodoTask> GetTaskAsync(int id);
        Task<TodoTask> CreateTaskAsync(TodoTask task);
        Task<TodoTask> UpdateTaskAsync(int id, TodoTask task);
        Task DeleteTaskAsync(int id);
    }
}
