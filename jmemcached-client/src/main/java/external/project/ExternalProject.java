package external.project;

import net.devstudy.jmemcached.client.Client;
import net.devstudy.jmemcached.client.impl.JMemcachedClientFactory;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class ExternalProject {
    public static void main(String[] args) throws Exception {
        // Подключаемся к серверу jmemcached, который работает на localhost:9010
        try (Client client = JMemcachedClientFactory.buildNewClient("localhost", 9010)) {

            client.put("test", "Hello world"); //Сохранить текст под ключом test на сервере
            System.out.println(client.get("test")); //Запросить объект по ключу test

            client.remove("test"); //Удалить объект по ключу test
            System.out.println(client.get("test")); //Должно отобразить null - объект удален

            client.put("test", "Hello world");
            client.put("test", new BusinessObject("TEST"));
            // Выполнить замену объекта по ключу test
            System.out.println(client.get("test")); // Увидим BusinessObject

            client.clear(); // Очистить все данные на сервере
            System.out.println(client.get("test")); //Должно отобразить null - объект удален

            client.put("devstudy", "Devstudy JMemcached", 2, TimeUnit.SECONDS); //сохранить текст и указать время жизни
            TimeUnit.SECONDS.sleep(3); //Заморозить текущий поток на 3 секунды
            System.out.println((client.get("devstudy"))); //Должно отобразить null - объект удален, истекто время жизни 2 сек

        }
    }

    private static class BusinessObject implements Serializable {
        private String name;

        public BusinessObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BusinessObject{");
            sb.append("name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
