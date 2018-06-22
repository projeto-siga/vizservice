# VizService

O VizService é um microserviço capaz de produzir gráficos SVG a partir de diagramas descritos na linguagem Dot. Para isso, ele executa o aplicativo do GraphViz, passando o diagrama como parâmetro. O resultado obtido é repassado na resposta do método /svg.

O VisService requer que o GraphViz seja instalado na máquina. Em cada sistema operacional a instalação será feita de uma forma diferente. No caso do Mac OS X, podemos proceder da seguinte maneira:

1. Instalar o [MacPorts](https://www.macports.org/)
2. Executar:
```shell
ports install graphviz
```

O vizservice tem apenas 2 métodos:
1. GET /test - Método de teste que não recebe nenhum parâmetro e produz um diagrama de exemplo
2. POST /svg - Método que recebe um POST com Content-Type=text/vnd.graphviz e um body com uma string que representa o Dot e produz um gráfico no formato SVG 

```shell
curl -X POST \
  http://localhost:8080/vizservice/svg \
  -H 'cache-control: no-cache' \
  -H 'content-type: text/vnd.graphviz' \
  -H 'postman-token: ae7de52a-45d0-f14b-b6b5-5edf32a06cca' \
  -d 'digraph "" { graph[ratio="0.4" tooltip="Documentos Relacionados" color="#e2eaee" bgcolor="#e2eaee" URL="javascript: bigmapRelacaoDocs();"]; node[fillcolor=white  style=filled]; edge[penwidth=2];  "OTZZ-MEM-2018/00002-A"[shape="rectangle"][label="MEM2-A"][color="red"][URL="exibir?sigla=OTZZ-MEM-2018/00002-A" penwidth=2][tooltip="OTZZ-MEM-2018/00002-A"]; "OTZZ-RHU-2018/00001-V01"[shape="rectangle"][label="RHU1-V01"][URL="exibir?sigla=OTZZ-RHU-2018/00001-V01" penwidth=2][tooltip="OTZZ-RHU-2018/00001-V01"]; "OTZZ-MEM-2018/00001-A"[shape="rectangle"][label="MEM1-A"][URL="exibir?sigla=OTZZ-MEM-2018/00001-A" penwidth=2][tooltip="OTZZ-MEM-2018/00001-A"]; "OTZZ-MEM-2018/00002-A" -> "OTZZ-RHU-2018/00001-V01"[style="dashed"][tooltip="Vincula&ccedil;&atilde;o"][color="gray"][dir=none]; "OTZZ-MEM-2018/00001-A" -> "OTZZ-MEM-2018/00002-A"[style="dashed"][tooltip="Juntada"][dir=back]; }'
```

A chamada ao VizService segue exatamente o mesmo contrato que o container Docker [omerio/graphviz-server](https://hub.docker.com/r/omerio/graphviz-server/), portanto, uma instalação do Projeto-Siga baseada em containers do Docker não precisará usar o VizServer, poderá simplesmente substituir esse artefato pelo graphviz-server.
