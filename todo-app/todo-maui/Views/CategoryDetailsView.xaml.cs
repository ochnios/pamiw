using todo_maui.ViewModels;

namespace todo_maui.Views
{
    public partial class CategoryDetailsView : ContentPage
    {
        public CategoryDetailsView(CategoryDetailsViewModel viewModel)
        {
            BindingContext = viewModel;
            InitializeComponent();
        }
    }
}
