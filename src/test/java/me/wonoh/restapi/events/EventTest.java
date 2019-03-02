package me.wonoh.restapi.events;


import lombok.ToString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void testFree(){
        // Given
        Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();

        // when
        event.update();

        // then
        assertThat(event.isFree()).isTrue();

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
    @Test
    public void testOffline(){
        Event event = Event.builder()
                .location("강남역 네이버 D2 스타트업 팩토리")
                .build();

        event.update();

        assertThat(event.isOffline()).isTrue();

        event = Event.builder()
                .build();

        event.update();

        assertThat(event.isOffline()).isFalse();
    }

}
