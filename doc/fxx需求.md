- [ ] 视频图片信息添加
  - 功能描述

    该接口用于向数据库中添加一条视频图片信息数据

  - url
、
    请求方式：POST

    url路径：ip:port/videopicture/add

  - 入参

     | 字段名      | 类型    | 是否必填 | 备注       |
            | ----------- | --------- | -------- | ------------------- |
     | videoId         | String  | 是       | 关联的视频id                          |
     | name            | String  | 是       | 图片名称                          |
     | url             | String  | 是       | 图片地址，默认为空               |
     | thumbnails_url  | Integer | 是       | 略缩图地址     |
     | order_no        | String  | 否       | 同一视频图片顺序 |
     | create_time     | String  | 否       | 非必填则按当前时间入库 |
     | update_time     | String  | 否       | 非必填则按当前时间入库 |

  - 出参

    | 字段名 | 类型    | 是否必填 | 备注       |
        | ------ | ------- | -------- | ---------- |
    | code   | Integer | 是       | http状态码 |
    | msg    | String  | 否       | 状态信息   |
    | data   | Object  | 否       | 传输数据   |

- [ ] 视频图片信息修改
  - 功能描述

    该接口用于修改一条视频图片信息

  - url

    请求方式：POST

    url路径：ip:port/comment/add

  - 入参

    | 字段名 | 类型    | 是否必填 | 备注       |
    | ------ | ------- | -------- | ---------- |
    | id   | String | 是 | 需要修改的视频图片id |
    | data | Object | 是 | 修改的内容| 

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

    url路径：ip:port/comment/add

  - 入参
    
    | 字段名      | 类型    | 是否必填 | 备注       |
          | ----------- | --------- | -------- | ------------------- |
    | id              | String  | 是       | 视频图片id
    | videoId         | String  | 否       | 关联的视频id                          |
    | name            | String  | 否       | 图片名称                          |
    | url             | String  | 否       | 图片地址，默认为空               |
    | thumbnails_url  | Integer | 否       | 略缩图地址     |
    | order_no        | String  | 否       | 同一视频图片顺序 |
    | create_time     | String  | 否       | 非必填则按当前时间入库 |
    | update_time     | String  | 否       | 非必填则按当前时间入库 

  - 出参

    | 字段名 | 类型    | 是否必填 | 备注       |
                | ------ | ------- | -------- | ---------- |
    | code   | Integer | 是       | http状态码 |
    | msg    | String  | 否       | 状态信息   |
    | data   | Object  | 否       | 传输数据   |



- [ ] 视频图片信息删除
    - 功能描述

      该接口通过Id删除视频图片列表信息

    - url

      请求方式：POST

      url路径：ip:port/videopicture/delete/{id}

    - 入参

      | 字段名      | 类型    | 是否必填 | 备注       |
                | ----------- | --------- | -------- | ------------------- |
      | id              | String  | 是       | 视频图片id

    - 出参

      | 字段名 | 类型    | 是否必填 | 备注       |
                      | ------ | ------- | -------- | ---------- |
      | code   | Integer | 是       | http状态码 |
      | msg    | String  | 否       | 状态信息   |
      | data   | Object  | 否       | 传输数据   |

- [ ] 视频图片信息批量删除
    - 功能描述

      该接口通过Id删除视频图片列表信息

    - url

      请求方式：POST

      url路径：ip:port/videopicture/deletemore

    - 入参

      | 字段名      | 类型    | 是否必填 | 备注       | 
                      | ----------- | --------- | -------- | ------------------- |
      | id              | String  | 是       | 视频图片id

    - 出参

      | 字段名 | 类型    | 是否必填 | 备注       |
                            | ------ | ------- | -------- | ---------- |
      | code   | Integer | 是       | http状态码 |
      | msg    | String  | 否       | 状态信息   |
      | data   | Object  | 否       | 传输数据   |