# Baffle：通用挡板工程

### Introduce

通过Annotation + AOP实现了挡板效果，该方式简单易用，配置灵活。本工程完全展现了通用灵活的的挡板效果。

### Guides

1. 挡板类实现与被代理类相同接口，比如：BaffleInvoiceServiceImpl作为挡板类，与InvoiceServiceImpl实现了相同的接口：InvoiceService；
2. 在被代理类增加注解@Baffle，比如：在InvoiceServiceImpl类增加注解，注解参数从配置文件application.yml读取；
3. 在application.yml中增加对应的配置参数；

