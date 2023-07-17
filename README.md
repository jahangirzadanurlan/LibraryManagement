# library-managment
![Untitled Diagram drawio (4)](https://github.com/jahangirzadanurlan/library-management/assets/103985861/4a3b2341-84cf-4f83-9fa6-cdd1642f7476)


Library managment
Rami Kışla Cd. 98/1   +90 212 934 38 55
library@gmail.com  https://ramikutuphanesi.gov.tr/




SCenario

Libraty Managment System

Mövcud problemlər

Ənənəvi kitabxana idarəsi insanlarımız üçün bir çox çətinliklər və vaxt itkisi yaradır. İnsanlar kitab almaq və ya rezerv etmək üçün kitabxanalara getməlidir. Bu vəziyyət həm onların vaxtlarını itirməsinə səbəb olur, həm də iş prosesini çətinləşdirir.

Problemin həllinin təsviri

E-kitabxana saytı yaratmaqla istifadəçilərə kitabları kəşf etmək, onları hesablarına əlavə etmək və asanlıqla almaq və ya oxumaq imkanı təqdim edəcəyik. Beləliklə, insanlar kitabxanayla bağlı istəklərini daha praktik, sürətli və marağla edə biləcəklər.

Yaradılacağ proyektdə bu 5 əsas problem öz həllini tapmalıdır.
⦁	İstifadəçi qeydiyyatı imkanı 
⦁	Kitab almaq,rezerv etmək və ya borc götürmək
⦁	Hesab əməliyyatlarını (var olan kitabın səbətə əlavə olunması, çıxarılması, sayının artırılması)
⦁	Kitabların, kategoriya və brendlərə görə müəyyən strukturda saxlanılması və bunlara görə axtarışı edə bilmək imkanı
⦁	Ödənişi keçirmək


Rollar:
1. İstifadəçi Rolları:User,Admin,Moderator

1. İstifadəçinin Məsuliyyətləri:
   - Kitabları nəzərdən keçirin: İstifadəçilər kitabxanada mövcud olan kitablara baxa bilərlər.
   - Axtarış: İstifadəçilər kitabları başlıq, müəllif və ya janr kimi müxtəlif meyarlara əsasən axtara bilərlər.
   - Yerdə saxlama/ehtiyat: İstifadəçilər hazırda əlçatmaz olan kitabları rezerv edə bilərlər.
   - Borc Kitabları: İstifadəçilər kitabları borc götürə bilərlər.
   - Kitabları qaytarın: İstifadəçilər götürdükləri kitabları kitabxanaya qaytara bilərlər.

2. Admin Məsuliyyətləri:
   - İstifadəçiləri idarə edin: Adminlər istifadəçi hesablarını yarada, yeniləyə və silə bilər.
   - Kitabları idarə edin: Adminlər kitabxana sistemində kitab əlavə edə, redaktə edə və silə bilər.
   - Qaytarmaları idarə edin: Adminlər İstifadəçi hesablarını yeniləyə və cərimələri/rüsumları idarə edə bilər.
   - Hesabatlar yaradın: Adminlər kitab inventarları, istifadəçi fəaliyyəti, cərimələr və s. haqqında hesabatlar yarada bilər.
   - Sistem Konfiqurasiyası: Adminlər kredit müddətləri və cərimə məbləğləri kimi sistem parametrlərini konfiqurasiya edə bilərlər.

3. Moderatorun Məsuliyyətləri:
   - Kitab İcmalı: Moderatorlar istifadəçi tərəfindən təqdim olunan kitab rəylərini və reytinqlərini nəzərdən keçirə bilərlər.

   - Kitab İstəklərini Təsdiqləyin: Moderatorlar istifadəçilər tərəfindən yerləşdirilmiş kitab sorğularını(hint:Rezerv zamanı) nəzərdən keçirə və təsdiq edə bilərlər.


Model:


 




Endpoints:
/user
    /books
         GET-all books
                           /{id}
                               GET – find by id
                                 /borrow
                                 /cart
                                    POST – add book to cart--save()                                  
                                 /buy     
                                /cart
                                    POST – add book to cart--save()
    /categories
                    GET – all category
                           /{id}
                               GET – find by id     
                              /brands
                                  GET – all brands
                                         /{id}
                                             GET – find by id
                                              /book
                                                    /{id}
                                                        GET – find by id
                                                          /borrow
                                                                   /cart
                                                                        POST – add book to cart--save()                                  
                                                           /buy     
                                                                 /cart
                                                                        POST – add book to cart--save()

                 
/admin
    /books
         GET-all books
         POST-save book
         /{id}
                PUT-update book
                DELETE-delete book 
    /users
         GET-all users
         POST-save user
         /{id}
                PUT-update user
                DELETE-delete user
                      /transactions
                              GET-all transactions
                       /fined
                               GET-fined
                               DELETE-forgive user :)                           


  
 /moderator
              /review
                   GET-all review
             /request
                   GET-all requests
                          /{id}          
                                                   PUT-requestin statusu guncellenecek.Eger 0 olsa istek siyahısından silinməlidir.Status 1 edilən kimi                                                                                                        həmin istəklə gələn kitab,kitab bazasına save olunacaq    
            / check
                   POST- bu zaman borrow_status=1 olan bookların borrow_time cədvəlində end_date<indiki vaxtdan olarsa kitabın bağlı olduğu usere cərimə tətbiq olunacaq.   
                      GET-Cərimə tətbiq olunmuş userlərin infolarını göstərsin.                      

/fəhlə
             /categories
                    GET – all category
                          POST – save category
                           /{id}
                               GET – find by id
                               PUT – update category
                               DELETE-delete category 
                                         /brands
                                                 GET – all brands
                                                   POST – save brand
                                                          /{id}
                                                                   GET – find by id
                                                                   PUT – update brand 
                                                                   DELETE-delete brand 
                                                                           /books
                                                                                   GET – all books or search(RequestParam)
                                                                                      POST – save book
                                                                                              /{id}
                                                                                                       GET – find by id
                                                                                                       PUT – update book
                                                                                                       DELETE-delete book 










