<#include "menu.ftlh">
<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">
            <div class="alert alert-danger">Неверные данные</div>
            <table>
                <tr>
                    <td>Path</td>
                    <td>${path}
                    </td>
                </tr>
                <tr>
                    <td>Error</td>
                    <td>${error}
                    </td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>${status}
                    </td>

                </tr>
                <tr>
                    <td>Message</td>
                    <td>${status}
                    </td>

                </tr>
                <tr>
                    <td>Exception</td>
                    <td>${exception}
                    </td>
                </tr>
                <tr>
                    <td>Trace</td>
                    <td>
                        <pre>${trace}</pre>
                    </td>
                </tr>
            </table>
        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

                       <!-- Top Widget -->
            <#include "widgets/topPostsWidget.ftlh">

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
<script src="https://cdn.tiny.cloud/1/i1uk7rne75xftqvv55jp2jzwxitnhcaba3oikilpy4q1vntm/tinymce/5/tinymce.min.js"
        referrerpolicy="origin"></script>
<script>
    tinymce.init({
        selector: '#postArea'
    });
</script>
</body>

</html>
