   $(document).ready(function(){
            
            var oldtext=['今日總覽','成功者故事','財經','時事','職場力']
            var newtext=['Today','Success','Business','News','Career']
            $(".sub").slideUp(0)
            for(i=0;i<$(".main").length;i++){
                $(".main:eq("+i+")").mouseover({id:i},function(e){
                    n=e.data.id
                    $(".main:eq("+n+")").text(newtext[n])
                    $(".sub:eq("+n+")").slideToggle()
                    $(".sub:not(:eq("+n+"))").slideUp()
                })
                $(".main").mouseout({id:i},function(e){
                    n=e.data.id
                   // $(".sub").slideUp(200)
                    $(".main:eq("+n+")").text(oldtext[n])
                })
            }
            $("#menu").css("width",$(".main").length*100)
        })