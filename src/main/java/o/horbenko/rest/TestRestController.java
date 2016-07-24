package o.horbenko.rest;

import o.horbenko.hibernate.dal.TestDALService;
import o.horbenko.hibernate.entity.TestEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;


@RestController
@RequestMapping(value = "/test")
public class TestRestController {

    private static Logger logger = Logger.getLogger(TestDALService.class.getName());

    private final TestDALService testDAL;


    @Autowired
    public TestRestController(TestDALService testDAL) {
        this.testDAL = testDAL;
    }


    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity loadAllSoft() {
        try {

            List<TestEntity> result = testDAL.getAll();

            return new ResponseEntity(result, HttpStatus.OK);


        } catch (Exception e) {
            logger.error("EXCEPTION: ", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

