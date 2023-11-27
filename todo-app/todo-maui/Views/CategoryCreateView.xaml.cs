using todo_maui.ViewModels;

namespace todo_maui.Views
{
    public partial class CategoryCreateView : ContentPage
    {
        public CategoryCreateView(CategoryCreateViewModel viewModel)
        {
            BindingContext = viewModel;
            InitializeComponent();
        }
    }
}
