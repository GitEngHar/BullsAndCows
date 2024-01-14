### アノテーションについて

アノテーションは付加情報
直後にあるものに対していろいろな情報を付与する

#### @RestControllerアノテーション

- 説明
  - コントローラークラスの結果をそのままブラウザに送信する
  - 本来はJSONやXMLなどを返す RESTインターフェース で使う

- コントローラークラスとは
  - /path と に応じた処理結果を返す1セットを持った機能

◇ 使用例
```java
@Controller
public class WebApp {
	//Pathと処理の関連を記述する
}
```

#### @GetMappingアノテーション

- 説明
  -  Getリクエストに対応するメソッドであることを表す
  指定パスのGETリクエストに対して処理を行うメソッドであることを表すアノテーション。
  - `@GetMapping("/hello")` と記述してGetリクエストが/helloに送られてきたら、直後のクラスが呼び出される。
  - これがURLパス名に対応する処理。このようなメソッドを「ハンドラーメソッド」

◇ 使用例
```java
@GetMapping("/")
public String GetAction(){
	// "/" にGetが送信された場合helloworldを返す
	return "helloworld";
}
```

#### @PostMappingアノテーション

- 説明
  - `@GetMapping` のPost版でhtmlからのPostリクエストに対して実行する処理

◇ 使用例
```java
@PostMapping("/new")
public ModelAndView PostAction(@RequestParam("number")String number,ModelAndView mv){
	String return_number = "new-" + number;
	mv.setViewName("test") // templateのtest.htmlを返す
	mv.addObject("return_number",return_number); // htmlのth:text="${return_number}"に値を入れる
	return mv; // 加工したtest.htmlを返す
}
```

#### @RequestParamアノテーション

- 説明
	- 引数で指定されたパラメータの値をメソッド実行前にクエリ文字列から取得
	メソッド引数へ値をセット(バインド)する
	("例 >> http://aaa?name=haru")

◇ 使用例  
name=haru の nameパラメータを受け取る値とするときは以下のような記述

```java
import org.springframework.web.bind.annotation.RestController;

public String sayHello(@RequestParam("name")String name) // 関数(アノテーション(パラム名)型 変数)
```

##### 複数値の場合

- 説明
	- 複数で渡す場合は、引数を ,(カンマ)区切りで記述する。
	httpでの記述は以下のようになる
	`http//aaa.com?name=haru&age=12`

◇ 使用例

```java
import org.springframework.web.bind.annotation.RestController;

public String sayHello(@RequestParam("name")String name,@RequestParam("age")String age) // 関数(アノテーション(パラム名)型 変数)
```

#### @PathVariable

- 説明
	- パスベースで値を渡すことができる。マッピングするパスに{}で **URIテンプレート変数** といって記述する。
	- `/` で値を渡すことができる
	`http//aaa.com/haru`
	上記のようにしてaaa.comへharuという値を渡せる

◇ 使用例

```java
import org.springframework.web.bind.annotation.PathVariable;
@GetMapping("/hello3/{name}")
public String Hello(@PathVariable("name")String name) {
  return "HelloWorld! " + name +"さん";
}
```

##### 複数値の場合

- 説明
  - `/`で階層ごとに値を記述することで複数値設定できる

◇ 使用例

```java
@GetMapping("/hello3/{name}/{age}")
public String Hello(@PathVariable("name")String name,@PathVariable("age")String age) {
  return "HelloWorld! " + name +"さんの年齢は " + age + "歳です!";
}
```

### テンプレートエンジン

- 説明
  - テンプレートエンジンとは何？
    - WebApp開発 は 主に実装が2種類のレイヤにわけられる
      - 入力データの処理
      - Web画面を作成する部分 ← ココに使われるのがテンプレートエンジン
        - コードの可読性の向上と管理を容易にするために用いられるテンプレート

様々なテンプレートエンジンがあるが今回は `Thymeleaf(タイムリーフ)` と呼ばれる

#### Thymeleaf

- 説明
  - html との親和性が高く、実際のファイルもhtmlである。しかし、独自の属性を持つ  
  - SpringBootでかなり利用されているテンプレートエンジン。JSP , Groovyも利用されている
  - `th:` のタグがあればthymeleafで利用され、thymeleafのテンプレートファイルから該当する機能を `th:(ココ)` を 探しコンテンツの生成を行う

##### 例:テキストの生成

`th:text` を span要素に設けることで変数に格納されている値をテキストとして出力する
`<span th:text="${name}">` で nameの値を出力する

- nameの値を渡すには(java to html(thymeleaf))
  - コントローラを記述する
  `@Controller`を用いて関数を記述。`@RestController`はRESTAPIでテキスト値を渡すものなため、HTMLデータを渡せる `@Controller` で記述する必要がある
  - ModelAndViewオブジェクト
  次に表示する画面 `view` と ビューに表示するデータ `Model` を用いる。
  `mv.setViewName("hello")`でビュー名(hello.html)を設定
  `mv.addObject("thの変数名",変数)`つまり`mv.addObject("name",getname)`
  ビューの名称とモデルデータをセットした html データを返す

#### データ構造化による引数の簡略化

before
```java
	public ModelAndView registr(@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("gender") int gender,
			@RequestParam("area") int area,
			@RequestParam("interest") int[] interests,
			@RequestParam("remark") String remarks,
			ModelAndView mv
			) 
```

after
```java
	public ModelAndView registr(
			@ModelAttribute RegistData registData,
			ModelAndView mv
			)
```

上記のように簡略化することができる  
そのためには構造化データを宣言するクラス(`RegistData`)と `Lombok` モジュールが必要になる  
データを宣言している `RegistData`クラスの記述は以下の通り  
 
```java
package com.example.demo;
import lombok.Data;

@Data
public class RegistData {
	private String name;
	private String password;
	private int gender;
	private int area;
	private int[] interests;
	private String remarks;
}
```

### Tips

#### 変数の型変換

int を 文字列へ変換する場合

```java
int old = 0;
String old_str = Integer.valueOf(old).toString()
```

#### 便利ツール SpringBootDevTools

変更した内容を再デプロイやサーバ再起動をせずとも、内容が即座に反映され結果を確認できる。


