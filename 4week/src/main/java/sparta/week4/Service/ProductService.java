package sparta.week4.Service;

import sparta.week4.Entity.Product;
import sparta.week4.Dto.ProductMypriceRequestDto;
import sparta.week4.ProductRepository;
import sparta.week4.Dto.ProductRequestDto;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    //객체 중복삭제!
    private  ProductRepository productRepository;

    public ProductService(){
        ProductRepository productRepository =  new ProductRepository();
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);
////ProductRepository를 불러와서 객체를 생성
//        ProductRepository productRepository = new ProductRepository();
//        DB에 저장할 객체를 넘겨주기 input:product output:
        productRepository.createProduct(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
//        ProductRepository productRepository = new ProductRepository(); 객체 중복삭제
        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        int myprice = requestDto.getMyprice();
        productRepository.updateMyprice(id, myprice);

        return product;
    }

    public List<Product> getProducts() throws SQLException {
//        ProductRepository productRepository = new ProductRepository();객체 중복삭제
        List<Product> products = productRepository.getProducts();

        return products;
    }
}
