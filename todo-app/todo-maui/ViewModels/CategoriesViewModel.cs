using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using System.Collections.ObjectModel;
using todo_maui.MessageBox;
using todo_maui.Models;
using todo_maui.Shared;
using todo_maui.Views;

namespace todo_maui.ViewModels
{
    public partial class CategoriesViewModel : ObservableObject
    {
        private readonly ICategoryService _categoryService;
        public ObservableCollection<Category> Categories { get; private set; }
        [ObservableProperty]
        private Category? selectedCategory;

        public CategoriesViewModel(ICategoryService categoryService)
        {
            _categoryService = categoryService;
            Categories = new ObservableCollection<Category>();
            GetCategories();
        }

        public async Task GetCategories()
        {
            Categories.Clear();
            try
            {
                var response = await _categoryService.GetCategoriesAsync();
                if (response.Data is not null)
                {
                    foreach (var t in response.Data)
                    {
                        Categories.Add(t);
                    }
                }
            }
            catch (Exception ex)
            {
                MauiMessageDialogService.ShowMessage("Failed to get categories.");
            }
        }

        [RelayCommand]
        public async Task ShowDetails(Category category)
        {
            await Shell.Current.GoToAsync(nameof(CategoryDetailsView), true, new Dictionary<string, object>
        {
            {"Category", category },
            {nameof(CategoriesViewModel), this }
        });

            SelectedCategory = category;
        }

        [RelayCommand]
        public async Task New()
        {
            SelectedCategory = new Category();
            await Shell.Current.GoToAsync(nameof(CategoryCreateView), true, new Dictionary<string, object>
        {
            {"Category", SelectedCategory },
            {nameof(CategoriesViewModel), this }
        });
        }
    }
}
