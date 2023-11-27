using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using todo_maui.Models;

namespace todo_maui.Shared
{
    public interface ICategoryService
    {
        Task<Results<Category>> GetCategoriesAsync();
        Task<Category> GetCategoryAsync(int id);
        Task<Category> CreateCategoryAsync(Category task);
        Task<Category> UpdateCategoryAsync(int id, Category task);
        Task DeleteCategoryAsync(int id);
    }
}
