<#include "menu.ftlh">
<!-- Page Content -->
<div class="container">
    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">
            <#if searchCat??>
                <h1 class="my-4"><small>Категория: <b>${searchCat}</b></small></h1>
            <#else>
                <h1 class="my-4"><small>Вы искали: <b>${searchName}</b></small></h1>
            </#if>

            <#if null_error??>
                <div class="alert alert-danger">${null_error}</div></#if>
            <#list search_posts_list as post>
                <!-- Blog Post -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h2 class="card-title">${post.title}</h2>
                        <img class="card-img-top" src="${post.img?no_esc}" alt="Card image cap">
                        <p class="card-text">${post.shortText?no_esc}</p>
                        <a href="/post/${post.id?c}" class="btn btn-primary">Read More &rarr;</a>
                    </div>
                    <div class="card-footer text-muted">
                        Posted on ${post.created} : Category: ${post.categoryName} --- Views: ${post.views} |
                        Likes: ${post.likes} | Author:
                        ${post.author}
                    </div>
                </div>
            </#list>
            <!-- Pagination -->
            <ul class="pagination justify-content-center mb-4">
                <li class="page-item">
                    <a class="page-link" href="#">&larr; Older</a>
                </li>
                <li class="page-item disabled">
                    <a class="page-link" href="#">Newer &rarr;</a>
                </li>
            </ul>

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Search Widget -->
            <#include "widgets/searchWidget.ftlh">
            <!-- Categories Widget -->
            <#include "widgets/сategoriesWidget.ftlh">

                       <!-- Top Widget -->
            <#include "widgets/topPostsWidget.ftlh">
            <!-- Comments Widget -->
            <#include "widgets/topCommentsWidget.ftlh">
        </div>

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
