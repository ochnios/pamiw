using todo_maui.ViewModels;

namespace todo_maui.Views
{
    public partial class CategoriesView : ContentPage
    {
        public CategoriesView(CategoriesViewModel viewModel)
        {
            InitializeComponent();
            BindingContext = viewModel;
        }
    }
}
