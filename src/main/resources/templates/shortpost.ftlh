<#-- @ftlvariable name="post" type="ua.org.jblog.dto.PostDto" -->
<!-- Blog Post -->
<div class="card mb-4">
    <div class="card-body">
        <h2 class="card-title">
            <p class="badge badge-warning">${post.categoryName}</p>
            ${post.title}
        </h2>
        <img class="card-img-top" src="${post.img?no_esc}" alt="Card image cap">
        <p class="card-text">${post.shortText?no_esc}</p>
        <a href="/post/${post.id?c}" class="btn btn-primary">Read More &rarr;</a>
    </div>
    <div class="card-footer text-muted">
        Опубліковано: ${post.created} |
        Переглядів: ${post.views?c} |
        Вподобало: ${post.likes?c} |
        Автор: ${post.author}
    </div>
</div>