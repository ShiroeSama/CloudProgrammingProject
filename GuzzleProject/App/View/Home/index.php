<?php
	use ShirOSBundle\Utils\Url\Url;
	
	/** @var Url $UrlModule */
?>

<section id="Mozaic">
    
    <div class="container">
	    <?php foreach ($pages as $page): ?>

            <section id="<?= $page['NAME']; ?>" class="item d-inline w-30 p-10">
                <a href="<?= $UrlModule->getUrl($page['URL']); ?>" class="btn btn-block btn-fourth btn-80"><?= $page['NAME']; ?></a>
            </section>
	
	    <?php endforeach; ?>
    </div>
    
</section>