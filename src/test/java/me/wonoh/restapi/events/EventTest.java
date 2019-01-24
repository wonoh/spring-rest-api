package me.wonoh.restapi.events;


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

}
