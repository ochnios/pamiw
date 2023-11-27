using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using todo_maui.MessageBox;
using todo_maui.Models;
using todo_maui.Shared;

namespace todo_maui.ViewModels
{
    [QueryProperty(nameof(TodoTask), nameof(TodoTask))]
    [QueryProperty(nameof(TaskViewModel), nameof(TaskViewModel))]
    public partial class TaskCreateViewModel : ObservableObject
    {
        private readonly ITaskService _taskService;
        private TasksViewModel _taskViewModel;

        public TaskCreateViewModel(ITaskService taskService, TasksViewModel taskViewModel)
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

        public async Task CreateTask()
        {
            var newTask = new TodoTask()
            {
                Title = TodoTask.Title,
                Description = TodoTask.Description,
                Status = TodoTask.Status,
                UserId = TodoTask.UserId,
                CategoryId = TodoTask.CategoryId
            };

            try
            {
                var result = await _taskService.CreateTaskAsync(newTask);
            }
            catch (Exception ex)
            {
                MauiMessageDialogService.ShowMessage("Failed to create task.");
            }

            await _taskViewModel.GetTasks();
        }

        [RelayCommand]
        public async Task Save()
        {
            await CreateTask();
            await Shell.Current.GoToAsync("../", true);
        }
    }
}
