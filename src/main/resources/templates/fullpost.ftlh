<#include "menu.ftlh">
<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4">
                <small>Подробно о новости:</small>
            </h1>
            <div class="card mb-4">
                <div class="card-body">
                    <h2 class="card-title">${post.title}</h2>
                    <img class="card-img-top" src="http://placehold.it/750x300" alt="Card image cap">
                    <p class="card-text">${post.fullText?no_esc}</p>
                </div>
                <div class="card-footer text-muted">
                    Posted on ${post.created} :
                    Category: ${post.categoryName} ---
                    Views: ${post.views?c} |
                    Likes: ${post.likes?c} |
                    Author: ${post.author}
                    <form action="/post/${post.id?c}" method="post">
                        <button name="likes" value="1" type="submit">+</button>
                        <button name="likes" value="-1" type="submit">-</button>
                    </form>
                </div>
            </div>
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
            <#include "searchWidget.ftlh">

            <!-- Categories Widget -->
            <#include "сategoriesWinget.ftlh">

            <!-- Side Widget -->
            <div class="card my-4">
                <h5 class="card-header">Side Widget</h5>
                <div class="card-body">
                    You can put anything you want inside of these side widgets. They are easy to use, and feature the
                    new Bootstrap 4 card containers!
                </div>
            </div>

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
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
