- [ ] 视频图片信息添加

    - 功能描述

      为当前视频进行视频海报添加，前端页面录入图片名称、图片地址、略缩图地址（预留字段）、当前视频下的图片展示顺序等。在添加成功后，向前端返回操作成功信息。**

    - url

      请求方式：POST 

      url路径：ip:port/picture/add 

    - 入参
    
      | 字段名      | 类型    | 是否必填 | 备注       |
           | ----------- | --------- | -------- | ------------------- |
      | videoId         | String  | 是       | 关联的视频id                          |
      | name            | String  | 是       | 图片名称                          |
      | url             | String  | 是       | 图片地址，默认为空               |
      | thumbnailsUrl  | Integer | 是       | 略缩图地址     |
      | orderNo        | String  | 否       | 同一视频图片顺序 |

    - 出参

      | 字段名 | 类型    | 是否必填 | 备注       |
          | ------ | ------- | -------- | ---------- |
      | code   | Integer | 是       | http状态码 |
      | msg    | String  | 否       | 状态信息   |
      | data   | Object  | 否       | 传输数据   |

- [ ] 视频图片信息修改
    - 功能描述

      该接口用于根据图片的id字段进行，图片内容信息的修改。

    - url

      请求方式：POST 

      url路径：ip:port/picture/{id}/update

    - 入参

      | 字段名 | 类型    | 是否必填 | 备注       |
          | ------ | ------- | -------- | ---------- |
      | id   | String | 是 | 需要修改的视频图片id |
      | videoId         | String  | 是       | 关联的视频id                          |
      | name            | String  | 是       | 图片名称                          |
      | url             | String  | 是       | 图片地址，默认为空               |
      | thumbnailsUrl  | Integer | 是       | 略缩图地址     |
      | orderNo        | String  | 否       | 同一视频图片顺序 |

    - 出参

      | 字段名 | 类型    | 是否必填 | 备注       |
          | ------ | ------- | -------- | ---------- |
      | code   | Integer | 是       | http状态码 |
      | msg    | String  | 否       | 状态信息   |
      | data   | Object  | 否       | 传输数据   |


- [ ] 视频图片信息列表查询
    - 功能描述

      该接口用于查询视频图片列表

    - url

      请求方式：POST

      url路径：ip:port/picture/list

    - 入参

      | 字段名      | 类型    | 是否必填 | 备注       |
          | ----------- | --------- | -------- | ------------------- |
      | videoId   | String  | 否       | 关联的视频id           |
      | name      | String  | 否       | 图片名称            |
      | page      | Integer | 否       | 页码               |
      | size      | Integer | 否       | 页面大小     |

    - 出参

      | 字段名 | 类型    | 是否必填 | 备注       |
          | ------ | ------- | -------- | ---------- |
      | code   | Integer | 是       | http状态码 |
      | msg    | String  | 否       | 状态信息   |
      | data   | Object  | 是       | 返回的数据  |

  data中的数据结构如下：

    ```json
    {
        "page" : 0,
        "size" : 9,
        "total" : 100,
        "data" : [
            {
                "id" : "xx",
                "videoId" : "xx",
                "name" : "xx",
                "url" : "xx",
                "thumbnailsUrl" : 123,
                "orderNo" : "同一视频图片顺序",
                "createTime" :  "2020-10-25 00:00:00",
                "updateTime" :  "2020-10-25 00:00:00"
            }
        ]
    }
    ```


- [ ] 视频图片信息删除

    - 功能描述

      该接口通过Id删除视频图片列表信息

    - url

      请求方式：GET

      url路径：ip:port/picture/delete/{id}

    - 入参

      | 字段名      | 类型    | 是否必填 | 备注       |
            | ----------- | --------- | -------- | ------------------- |
      | id              | String  | 是       | 视频图片id|

    - 出参

      | 字段名 | 类型    | 是否必填 | 备注       |
            | ------ | ------- | -------- | ---------- |
      | code   | Integer | 是       | http状态码 |
      | msg    | String  | 否       | 状态信息   |
      | data   | Object  | 否       | 传输数据   |

- [ ] 视频图片信息批量删除
    - 功能描述

      该接口通过Id批量删除视频图片列表信息

    - url

      请求方式：POST

      url路径：ip:port/picture/delete-more

    - 入参

      | 字段名      | 类型    | 是否必填 | 备注       |
            | ----------- | --------- | -------- | ------------------- |
      | ids | String | 是 | 需要批量删除的图片id，以逗号进行数据分隔。如："1,2,3,4,5"。 |

    - 出参

      | 字段名 | 类型    | 是否必填 | 备注       |
            | ------ | ------- | -------- | ---------- |
      | code   | Integer | 是       | http状态码 |
      | msg    | String  | 否       | 状态信息   |
      | data   | Object  | 否       | 传输数据   |