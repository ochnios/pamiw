using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using todo_maui.MessageBox;
using todo_maui.Models;
using todo_maui.Shared;

namespace todo_maui.ViewModels
{
    [QueryProperty(nameof(Category), nameof(Category))]
    [QueryProperty(nameof(CategoryViewModel), nameof(CategoryViewModel))]
    public partial class CategoryDetailsViewModel : ObservableObject
    {
        private readonly ICategoryService _categoryService;
        private CategoriesViewModel _categoryViewModel;

        public CategoryDetailsViewModel(ICategoryService categoryService, CategoriesViewModel categoryViewModel)
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

        public async Task DeleteCategory()
        {
            await _categoryService.DeleteCategoryAsync(Category.Id);
            await _categoryViewModel.GetCategories();
        }

        public async Task UpdateCategory()
        {
            var categoryToUpdate = new Category()
            {
                Id = Category.Id,
                Name = Category.Name,
            };

            try
            {
                var response = await _categoryService.UpdateCategoryAsync(Category.Id, categoryToUpdate);
            }
            catch (Exception ex)
            {
                MauiMessageDialogService.ShowMessage("Failed to update categories.");
            }

            await _categoryViewModel.GetCategories();
        }

        [RelayCommand]
        public async Task Save()
        {
            await UpdateCategory();
            await Shell.Current.GoToAsync("../", true);
        }

        [RelayCommand]
        public async Task Delete()
        {
            await DeleteCategory();
            await Shell.Current.GoToAsync("../", true);
        }
    }
}
