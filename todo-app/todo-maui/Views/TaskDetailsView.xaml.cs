using todo_maui.ViewModels;

namespace todo_maui.Views
{
    public partial class TaskDetailsView : ContentPage
    {
        public TaskDetailsView(TaskDetailsViewModel viewModel)
        {
            InitializeComponent();
            BindingContext = viewModel;
        }
    }
}
