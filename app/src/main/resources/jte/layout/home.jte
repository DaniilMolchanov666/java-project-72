@import gg.jte.Content
@import io.javalin.http.Context;

@param Context c
@param Content content

<html lang="ru">
<head>
    <div class="p-3 mb-2 bg-dark text-white">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
              crossorigin="anonymous">
    <title>Анализатор страниц</title>
        <ul class="nav nav-underline">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/">Анализатор страниц</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/urls">Сайты</a>
            </li>
        </ul>
        @if(c.sessionAttribute("incorrect url") != null)
            <div class="alert alert-danger" role="alert">
                ${c.sessionAttribute("incorrect url").toString()}
            </div>
        @endif
    <h1> Анализатор страниц </h1>
    <h6>Бесплатно проверяйте сайты на SEO пригодность</h6>
        <form action="/urls" method="post">
            <div>
                <label>
                    <p1>
                    <input type="text" name="url" placeholder="Ссылка" size="50"/>
                    <button type="submit" class="btn btn-primary">Проверить</button>
                    </p1>
                </label>
            </div>
        </form>
    </div>
</head>
</html>
