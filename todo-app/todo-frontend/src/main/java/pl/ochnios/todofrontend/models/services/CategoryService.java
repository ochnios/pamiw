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
        CategoryDto[] result = ObjectMapperUtil.getObjectMapper().readValue(resultJson, CategoryDto[].class);

        return List.of(result);
    }

    public List<CategoryDto> getById(String id) throws Exception {
        String resultJson = HttpClientUtil.makeGetRequest(new URI(Consts.CATEGORIES_EP + "/" + id));
        CategoryDto result = ObjectMapperUtil.getObjectMapper().readValue(resultJson, CategoryDto.class);

        return List.of(result);
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

    public List<CategoryDto> update(String id, String name) throws Exception {
        CategoryDto category = new CategoryDto(-1, name);
        String categoryJson = ObjectMapperUtil.getObjectMapper().writeValueAsString(category);

        String resultJson = HttpClientUtil.makePatchRequest(new URI(Consts.CATEGORIES_EP + "/" + id), categoryJson);
        CategoryDto result = ObjectMapperUtil.getObjectMapper().readValue(resultJson, CategoryDto.class);

        return List.of(result);
    }

    public void delete(String id) throws Exception {
        String result = HttpClientUtil.makeDeleteRequest(new URI(Consts.CATEGORIES_EP + "/" + id));
    }
}
