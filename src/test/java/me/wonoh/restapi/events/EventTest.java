package me.wonoh.restapi.events;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(JUnitParamsRunner.class)
public class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder()
                .name("Spring rest api")
                .description("rest api dev with spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean(){
            Event event = new Event();
            String name = "test";
            String des = "rest";
            event.setName(name);
            event.setDescription(des);
            assertThat(event.getName()).isEqualTo(name);
            assertThat(event.getDescription()).isEqualTo(des);
    }
    @Test
    @Parameters
    public void testFree(int basePrice,int maxPrice,boolean isFree){
        // Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        // when
        event.update();

        // then
        assertThat(event.isFree()).isEqualTo(isFree);

        // Given
        event = Event.builder()
                .basePrice(100)
                .maxPrice(0)
                .build();

        // when
        event.update();

        // then
        assertThat(event.isFree()).isFalse();

        // Given
        event = Event.builder()
                .basePrice(0)
                .maxPrice(100)
                .build();

        // when
        event.update();

        // then
        assertThat(event.isFree()).isFalse();
    }
    private Object[] parametersForTestFree(){
        return new Object[]{
          new Object[]{0,0,true},
          new Object[]{100,0,false},
          new Object[]{0,100,false},
          new Object[]{100,100,false}
        };
    }
    private Object[] parametersForTestOffline(){
        return new Object[]{
                new Object[]{"강남",true},
                new Object[]{null,false},
                new Object[]{"    ",false}
        };
    }
    @Test
    @Parameters
    public void testOffline(String location,boolean isOffline){
        Event event = Event.builder()
                .location(location)
                .build();

        event.update();

        assertThat(event.isOffline()).isEqualTo(isOffline);

        event = Event.builder()
                .build();

        event.update();

        assertThat(event.isOffline()).isFalse();
    }

}
