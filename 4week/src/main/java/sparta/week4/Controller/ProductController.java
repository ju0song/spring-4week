package sparta.week4.Controller;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import sparta.week4.Entity.Product;
import sparta.week4.Dto.ProductMypriceRequestDto;
import sparta.week4.Dto.ProductRequestDto;
import sparta.week4.Service.ProductService;

import java.sql.SQLException;
import java.util.List;


@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductController {
    //객체 중복삭제
   private final ProductService productService;

    public ProductController(){
        ProductService productService =  new ProductService();
        this.productService = productService;

    }

    // 신규 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        //1.서비스로 역할을 넘겨주기(서비스 선언)
//        ProductService productService = new ProductService(); 객체 중복삭제
        //2. input: requestDto  /output CreateProduct
//           CreateProduct에 클라이언트에게받은 requestdto를 보내줘야함

        Product product = productService.createProduct(requestDto);


// 응답 보내기
        return product;
    }

    // 설정 가격 변경
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
//        ProductService productService = new ProductService(); //서비스를 넘겨주기 객체 중복삭제
        Product product = productService.updateProduct(id, requestDto);

// 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() throws SQLException {
//        ProductService productService = new ProductService(); 객체 중복삭제
        List<Product> products = productService.getProducts();

// 응답 보내기
        return products;
    }
}