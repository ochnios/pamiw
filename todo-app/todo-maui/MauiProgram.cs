﻿using Microsoft.Extensions.Logging;
using todo_maui.Shared;
using todo_maui.ViewModels;
using todo_maui.Views;

namespace todo_maui
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                    fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
                })
                .AddServices()
                .AddViewModels()
                .AddViews();

#if DEBUG
            builder.Logging.AddDebug();
#endif

            return builder.Build();
        }

        private static MauiAppBuilder AddServices(this MauiAppBuilder builder)
        {
            builder.Services.AddSingleton<ITaskService, TaskService>();
            builder.Services.AddSingleton<ICategoryService, CategoryService>();

            builder.Services.AddSingleton(sp => new HttpClient { BaseAddress = new Uri("http://localhost:8080/api/") });

            return builder;
        }

        private static MauiAppBuilder AddViewModels(this MauiAppBuilder builder)
        {
            builder.Services.AddSingleton<TasksViewModel>();
            builder.Services.AddSingleton<CategoriesViewModel>();

            builder.Services.AddTransient<TaskDetailsViewModel>();
            builder.Services.AddTransient<TaskCreateViewModel>();
            builder.Services.AddTransient<CategoryDetailsViewModel>();
            builder.Services.AddTransient<CategoryCreateViewModel>();

            return builder;
        }

        private static MauiAppBuilder AddViews(this MauiAppBuilder builder)
        {
            builder.Services.AddSingleton<TasksView>();
            builder.Services.AddSingleton<CategoriesView>();

            builder.Services.AddTransient<TaskDetailsView>();
            builder.Services.AddTransient<TaskCreateView>();
            builder.Services.AddTransient<CategoryDetailsView>();
            builder.Services.AddTransient<CategoryCreateView>();

            return builder;
        }
    }
}