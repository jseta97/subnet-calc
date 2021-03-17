
const express = require("express")
const app = express()
const fs = require('fs')

app.use(express.static("./public")) 
const port = process.env.PORT || 3000;

app.get("/hello", async (req, res) => {
console.log('hehe')
res.status(200).send("ok");
});

app.get("/download", async (req, res) => {

  const url = req.query.url;
  const from = parseInt(req.query.from);
  const to = parseInt(req.query.to);
  console.log(req.query);
  let err = false;
  let params = false;
  try {
    await page.goto(url);
    await page.waitForTimeout(15000);
    for(let frame of page.frames())
    {
       ile = await frame.$('.afterInput');
       input = await frame.$('#openSDPagerInputContainer2 > input[type=text]')
       lewo = await frame.$('div.openSDToolbar.zoomer-default-position > div.open-sd-actions-wrapper.toolbar-position > div.openSDPager > span.previous.pager-icon.fs-civ-circle-chevron-left.enabled');
       prawo = await frame.$('div.openSDToolbar.zoomer-default-position > div.open-sd-actions-wrapper.toolbar-position > div.openSDPager > span.next.pager-icon.fs-civ-circle-chevron-right.enabled');
       load = await frame.$('#saveLi > a');
        if(ile&&input&&lewo&&prawo&&load)
        {
            break;
        }
    }
  let ileZdjec = await ile.textContent();
  ileZdjec = ileZdjec.replace(/\D/g,'');
  allPhotos = ileZdjec;
  if(from>ileZdjec || to>ileZdjec || from>to || from < 0 ){
    params = true;
    throw error;
  }

}
catch(error){
  err = true;
}
if(err===false) {
res.status(200).send("ok");
for(let i =0; i<10;i++){
  partialDownloadPromises.push(new getPromise());
}
await download(from,to);
}
else {
if(!params){
res.status(500).send(`Invalid url`)
}
else {
  res.status(501).send(`Invalid params`);
}
}
});

process.on('SIGINT', function() {
  console.log( "\nGracefully shutting down from SIGINT (Ctrl-C)" );
  process.exit(1);
});

const server = app.listen(port, () => {
  console.log(`Listening on port ${port}!`);
});
