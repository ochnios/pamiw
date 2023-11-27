using todo_maui.Views;

namespace todo_maui
{
    public partial class AppShell : Shell
    {
        public AppShell()
        {
            InitializeComponent();

            Routing.RegisterRoute(nameof(TaskDetailsView), typeof(TaskDetailsView));
            Routing.RegisterRoute(nameof(TaskCreateView), typeof(TaskCreateView));

            Routing.RegisterRoute(nameof(CategoryDetailsView), typeof(CategoryDetailsView));
            Routing.RegisterRoute(nameof(CategoryCreateView), typeof(CategoryCreateView));
        }
    }
}