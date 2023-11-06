package pl.ochnios.todofrontend.models.services;

import pl.ochnios.todofrontend.core.Consts;
import pl.ochnios.todofrontend.models.dtos.CategoryDto;
import pl.ochnios.todofrontend.models.services.shared.HttpClientUtil;
import pl.ochnios.todofrontend.models.services.shared.ObjectMapperUtil;

import java.net.URI;
import java.util.List;

public class CategoryService {
    public List<CategoryDto> getAll() throws Exception {
        String resultJson = HttpClientUtil.makeGetRequest(new URI(Consts.CATEGORIES_EP));
        CategoryDto[] categories = ObjectMapperUtil.getObjectMapper().readValue(resultJson, CategoryDto[].class);

        return List.of(categories);
    }

    public List<CategoryDto> getById(String id) {
        throw new UnsupportedOperationException();
    }

    public List<CategoryDto> getByName(String name) {
        throw new UnsupportedOperationException();
    }

    public List<CategoryDto> create(String name) throws Exception {
        CategoryDto category = new CategoryDto(-1, name);
        String categoryJson = ObjectMapperUtil.getObjectMapper().writeValueAsString(category);

        String resultJson = HttpClientUtil.makePostRequest(new URI(Consts.CATEGORIES_EP), categoryJson);
        CategoryDto result = ObjectMapperUtil.getObjectMapper().readValue(resultJson, CategoryDto.class);

        return List.of(result);
    }

    public List<CategoryDto> update(String id, String name) {
        throw new UnsupportedOperationException();
    }

    public void delete(String id) {
        throw new UnsupportedOperationException();
    }
}
