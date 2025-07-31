package IntegracionBackFront.backfront.Controller.CategoriesController;

import IntegracionBackFront.backfront.Models.DTO.Categories.CategoryDTO;
import IntegracionBackFront.backfront.Services.Categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("apiCategory")
public class CategoriesController {
    @Autowired
    private CategoryService catService;

    /**
     * Obtener las categories
     * @Param 1 definir la pagina por defecto
     * @Param 2 definir el tamaño de la pagina o items por pagina
     * @Return Una respuesta de la entidad paginada
     */
    @GetMapping("/getCategories")
    private ResponseEntity<Page<CategoryDTO>> getCategories(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size
    ){
        if (size<=0 || size >50){
            ResponseEntity.badRequest().body(Map.of("status", "El tamaño de la pagina debe de estar entre 1 y 50"));
            return ResponseEntity.ok(null);
        }
        Page<CategoryDTO> category = catService.getAllCategories(page,size);
        if (category == null){
            ResponseEntity.badRequest().body(Map.of("status", "No hay categorias registradas"));
        }
        return ResponseEntity.ok(category);
    }
}
