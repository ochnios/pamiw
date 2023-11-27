using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using todo_maui.MessageBox;
using todo_maui.Models;
using todo_maui.Shared;

namespace todo_maui.ViewModels
{
    [QueryProperty(nameof(TodoTask), nameof(TodoTask))]
    [QueryProperty(nameof(TaskViewModel), nameof(TaskViewModel))]
    public partial class TaskDetailsViewModel : ObservableObject
    {
        private readonly ITaskService _taskService;
        private TasksViewModel _taskViewModel;

        public TaskDetailsViewModel(ITaskService taskService, TasksViewModel taskViewModel)
        {
            _taskService = taskService;
            _taskViewModel = taskViewModel;
        }

        public TasksViewModel TaskViewModel
        {
            get
            {
                return _taskViewModel;
            }
            set
            {
                _taskViewModel = value;
            }
        }

        [ObservableProperty]
        TodoTask todoTask;

        public async Task DeleteTask()
        {
            await _taskService.DeleteTaskAsync(TodoTask.Id);
            await _taskViewModel.GetTasks();
        }

        public async Task UpdateTask()
        {
            var taskToUpdate = new TodoTask()
            {
                Id = TodoTask.Id,
                Title = TodoTask.Title,
                Description = TodoTask.Description,
                Status = TodoTask.Status,
                UserId = TodoTask.UserId,
                CategoryId = TodoTask.CategoryId
            };

            try
            {
                var response = await _taskService.UpdateTaskAsync(TodoTask.Id, taskToUpdate);
            }
            catch (Exception ex)
            {
                MauiMessageDialogService.ShowMessage("Failed to update tasks.");
            }

            await _taskViewModel.GetTasks();
        }

        [RelayCommand]
        public async Task Save()
        {
            await UpdateTask();
            await Shell.Current.GoToAsync("../", true);
        }

        [RelayCommand]
        public async Task Delete()
        {
            await DeleteTask();
            await Shell.Current.GoToAsync("../", true);
        }
    }
}
