@import hexlet.code.dto.Urls
@import hexlet.code.model.*
@import gg.jte.Content
@param Urls urls

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
    </div>
</head>
        <body>
        <h1>
            Сайты
        </h1>
            @if(urls.getFlash().isPresent())
                @if(urls.getFlash().get().equals("Данная страница уже существует!"))
                    <div class="alert alert-info" role="alert">
                        ${urls.getFlash().get()}
                    </div>
                @else
                    @if(urls.getFlash().get().equals("Страница успешно создана!"))
                        <div class="alert alert-success" role="alert">
                            ${urls.getFlash().get()}
                        </div>
                    @endif
                @endif
            @endif
        <table class="table table-striped mt-5">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Email</th>
                <th scope="col">created at</th>
            </tr>
            </thead>
            <tbody>
            @if(urls.getUrls().isEmpty())
                <tr>
                    <th scope="row">  </th>
                    <td>  </td>
                    <td>  </td>
                </tr>
            @endif
            @for(Url url: urls.getUrls())
                <tr>
                    <th scope="row"> ${url.getId()} </th>
                    <td> <a href="/urls/${url.getId()}">${url.getName()}</a> </td>
                    <td>
                            ${url.getCreatedAt().toString()}
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
        </body>
</html>
