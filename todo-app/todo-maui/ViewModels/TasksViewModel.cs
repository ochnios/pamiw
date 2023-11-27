using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using System.Collections.ObjectModel;
using todo_maui.MessageBox;
using todo_maui.Models;
using todo_maui.Shared;
using todo_maui.Views;

namespace todo_maui.ViewModels
{
    public partial class TasksViewModel : ObservableObject
    {
        private readonly ITaskService _taskService;
        public ObservableCollection<TodoTask> Tasks { get; private set; }
        [ObservableProperty]
        private TodoTask? selectedTask;

        public TasksViewModel(ITaskService taskService)
        {
            _taskService = taskService;
            Tasks = new ObservableCollection<TodoTask>();
            GetTasks();
        }

        public async Task GetTasks()
        {
            Tasks.Clear();
            try
            {
                var response = await _taskService.GetTasksAsync();
                if (response.Data is not null)
                {
                    foreach (var t in response.Data)
                    {
                        Tasks.Add(t);
                    }
                }
            }
            catch (Exception ex)
            {
                MauiMessageDialogService.ShowMessage("Failed to get tasks.");
            }
        }

        [RelayCommand]
        public async Task ShowDetails(TodoTask task)
        {
            await Shell.Current.GoToAsync(nameof(TaskDetailsView), true, new Dictionary<string, object>
        {
            {"TodoTask", task },
            {nameof(TasksViewModel), this }
        });

            SelectedTask = task;
        }

        [RelayCommand]
        public async Task New()
        {
            SelectedTask = new TodoTask();
            await Shell.Current.GoToAsync(nameof(TaskCreateView), true, new Dictionary<string, object>
        {
            {"TodoTask", SelectedTask },
            {nameof(TasksViewModel), this }
        });
        }
    }
}
