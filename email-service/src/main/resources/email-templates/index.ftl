<#import "layout/layout.ftl" as layout>
<@layout.default>
    <table class="email-wrapper" width="100%" cellpadding="0" cellspacing="0" role="presentation">
        <tr>
            <td align="center">
                <table class="email-content" width="100%" cellpadding="0" cellspacing="0" role="presentation">
                    <tr>
                        <td class="email-masthead"></td>
                    </tr>
                    <!-- Email Body -->
                    <tr>
                        <td class="email-body" width="1000" cellpadding="0" cellspacing="0">
                            <table class="email-body_inner" align="center" width="1000" cellpadding="0" cellspacing="0"
                                   role="presentation">
                                <!-- Body content -->
                                <tr>
                                    <td class="content-cell">
                                        <div class="f-fallback">
                                            <h1>Available email template</h1>
                                            <table   width="100%" role="presentation">
                                                <thead>
                                                    <tr>
                                                        <td>MAIL</td>
                                                        <td>GCLOUD PUBSUB</td>
                                                        <td>AWS SQS</td>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <tr style="padding-bottom: 10px;">
                                                        <td>
                                                            <a href="/template/welcome">Welcome</a>
                                                        </td>
                                                        <td>
                                                            <form action="/template/welcome/send/gcloud-pubsub" method="post">
                                                                <button type="submit">Send</button>
                                                            </form>

                                                        </td>
                                                        <td>
                                                            <form action="/template/welcome/send/aws-sqs" method="post">
                                                                <button type="submit">Send</button>
                                                            </form>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <a href="/template/password-reset">Password reset</a>
                                                        </td>
                                                        <td>
                                                            <form action="/template/password-reset/send/gcloud-pubsub" method="post">
                                                                <button type="submit">Send</button>
                                                            </form>
                                                        </td>
                                                        <td>
                                                            <form action="/template/password-reset/send/aws-sqs" method="post">
                                                                <button type="submit">Send</button>
                                                            </form>
                                                        </td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="email-masthead"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</@layout.default>
