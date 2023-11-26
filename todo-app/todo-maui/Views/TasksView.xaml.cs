using todo_maui.ViewModels;

namespace todo_maui.Views
{
    public partial class TasksView : ContentPage
    {
        public TasksView(TasksViewModel viewModel)
        {
            InitializeComponent();
            BindingContext = viewModel;
        }
    }
}
