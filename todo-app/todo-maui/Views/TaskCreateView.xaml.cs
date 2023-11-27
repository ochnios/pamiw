using todo_maui.ViewModels;

namespace todo_maui.Views
{
    public partial class TaskCreateView : ContentPage
    {
        public TaskCreateView(TaskCreateViewModel viewModel)
        {
            BindingContext = viewModel;
            InitializeComponent();
        }
    }
}
