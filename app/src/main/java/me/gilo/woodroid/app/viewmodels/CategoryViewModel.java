package me.gilo.woodroid.app.viewmodels;

import androidx.lifecycle.ViewModel;
import me.gilo.woodroid.app.common.WooLiveData;
import me.gilo.woodroid.app.repo.CategoryRepository;
import me.gilo.woodroid.models.Category;
import me.gilo.woodroid.models.filters.ProductCategoryFilter;

import javax.inject.Inject;
import java.util.List;


public final class CategoryViewModel extends ViewModel {
    private final CategoryRepository categoryRepository;

    @Inject
    CategoryViewModel(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    public WooLiveData<Category> create(Category category) {
        return categoryRepository.create(category);
    }


    public WooLiveData<Category> category(int id) {
        return categoryRepository.category(id);
    }

    public WooLiveData<List<Category>> categories() {
        return categoryRepository.categories();
    }

    public WooLiveData<List<Category>> categories(ProductCategoryFilter productCategoryFilter) {
        return categoryRepository.categories(productCategoryFilter);
    }

    public WooLiveData<Category> update(int id, Category category) {
        return categoryRepository.update(id, category);
    }

    public WooLiveData<Category> delete(int id) {
        return categoryRepository.delete(id);
    }

    public WooLiveData<Category> delete(int id, boolean force) {
        return categoryRepository.delete(id, force);
    }

}