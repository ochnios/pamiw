using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using todo_maui.MessageBox;
using todo_maui.Models;
using todo_maui.Shared;

namespace todo_maui.ViewModels
{
    [QueryProperty(nameof(Category), nameof(Category))]
    [QueryProperty(nameof(CategoriesViewModel), nameof(CategoriesViewModel))]
    public partial class CategoryCreateViewModel : ObservableObject
    {
        private readonly ICategoryService _categoryService;
        private CategoriesViewModel _categoryViewModel;

        public CategoryCreateViewModel(ICategoryService categoryService, CategoriesViewModel categoryViewModel)
        {
            _categoryService = categoryService;
            _categoryViewModel = categoryViewModel;
        }

        public CategoriesViewModel CategoryViewModel
        {
            get
            {
                return _categoryViewModel;
            }
            set
            {
                _categoryViewModel = value;
            }
        }

        [ObservableProperty]
        Category category;

        public async Task CreateCategory()
        {
            var newCategory = new Category()
            {
                Name = Category.Name
            };

            try
            {
                var result = await _categoryService.CreateCategoryAsync(newCategory);
            }
            catch (Exception ex)
            {
                MauiMessageDialogService.ShowMessage("Failed to create category.");
            }

            await _categoryViewModel.GetCategories();
        }

        [RelayCommand]
        public async Task Save()
        {
            await CreateCategory();
            await Shell.Current.GoToAsync("../", true);
        }
    }
}
