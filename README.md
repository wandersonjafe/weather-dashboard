# 🌤️ Spring Boot Weather Dashboard

Um projeto simples e moderno desenvolvido com **Spring Boot** e **Thymeleaf** para buscar e exibir informações de clima de qualquer cidade do mundo. Utiliza a API Open-Meteo para geocodificação e dados meteorológicos.

Este projeto demonstra a implementação de boas práticas em Java, utilizando **Records** para Data Transfer Objects (DTOs) e a arquitetura MVC (Model-View-Controller) no Spring.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+ (necessário para o uso de Records)
* **Framework:** Spring Boot 3.x
* **Template Engine:** Thymeleaf
* **Requisições HTTP:** `RestTemplate`
* **APIs Externas:**
    * [Open-Meteo Geocoding API](https://open-meteo.com/en/docs/geocoding-api)
    * [Open-Meteo Weather API](https://open-meteo.com/en/docs/weather-api)

---

## 📦 Estrutura do Projeto

O projeto segue a estrutura padrão do Spring Boot:

* `src/main/java/`: Contém a lógica de negócio e controladores.
    * `controller/`: Lida com as requisições HTTP e roteamento.
    * `service/`: Contém a lógica de integração com as APIs externas.
    * `model/`: **Utiliza Records** (`WeatherData`, `WeatherDetails`) para garantir a tipagem e imutabilidade dos DTOs.
* `src/main/resources/`: Contém os recursos estáticos e templates.
    * `static/`: Contém o CSS (`style.css`) e imagens.
    * `templates/`: Contém o template HTML (`index.html`) processado pelo Thymeleaf.

---

## 🚀 Como Executar o Projeto Localmente

Siga estas etapas para clonar e rodar a aplicação em seu ambiente:

### Pré-requisitos

* Java Development Kit (JDK) 17 ou superior.
* Maven ou Gradle (para gerenciar dependências).
* Uma IDE (IntelliJ IDEA, VS Code ou Eclipse) é recomendada.

### 1. Clonar o Repositório

```bash
git clone [https://www.youtube.com/shorts/3mMG25WHLkU](https://www.youtube.com/shorts/3mMG25WHLkU)
cd weather-dashboard-springboot
