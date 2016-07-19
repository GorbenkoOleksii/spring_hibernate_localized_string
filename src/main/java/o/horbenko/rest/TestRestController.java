package o.horbenko.rest;

import o.horbenko.exception.PersistanceException;
import o.horbenko.hibernate.dal.TestDALService;
import o.horbenko.hibernate.entity.TestEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


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

            System.out.println(LocaleContextHolder.getLocale().toString());
            List<TestEntity> result = testDAL.getAll();

            return new ResponseEntity(result, HttpStatus.OK);


        } catch (Exception e) {
            logger.error("EXCEPTION: ", e);
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(
            value = "/change",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity changeLocalization() {
        try {

            LocaleContextHolder.setLocale(Locale.US, true);


            System.out.println(LocaleContextHolder.getLocale().toString());
            return new ResponseEntity(HttpStatus.OK);


        } catch (Exception e) {
            logger.error("EXCEPTION: ", e);
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

